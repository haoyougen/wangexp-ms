/**
 * 
 */
package com.wangexp.services;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.config.gui.ArgumentsPanel;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.gui.LoopControlPanel;
import org.apache.jmeter.control.gui.TestPlanGui;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.Header;
import org.apache.jmeter.protocol.http.control.HeaderManager;
import org.apache.jmeter.protocol.http.gui.HeaderPanel;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.samplers.SampleSaveConfiguration;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.gui.ThreadGroupGui;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangshuguang
 *
 */
public class TestUtils {
	private static Logger logger = LoggerFactory.getLogger(TestUtils.class);
	private static final String HOST_NAME = "localhost";
	private static int port = 8080;
	private static final String JMETER_HOME = "E:/grpc/apache-jmeter-3.2/";
	private static final String JMETER_PATH = "E:/grpc/apache-jmeter-3.2/bin/";

	@Before
	public void init() {
		JMeterUtils.loadJMeterProperties(JMETER_HOME + "bin/jmeter.properties");
		JMeterUtils.loadJMeterProperties(JMETER_HOME + "bin/saveservice.properties");
		JMeterUtils.setJMeterHome(JMETER_HOME);
		JMeterUtils.initLocale();
	}

	@Test
	public void login() {
		logger.info("start invoke test");
		StandardJMeterEngine jmeter = new StandardJMeterEngine();

		// JMeter Test Plan, basic all u JOrphan HashTree
		HashTree testPlanTree = new HashTree();

		// HTTP Sampler
		HTTPSamplerProxy httpSampler = new HTTPSamplerProxy();
		httpSampler.setProtocol("HTTP");
		// httpSampler.setArguments(getLoginParameters());
		httpSampler.addArgument("login_name", "nn1");
		httpSampler.addArgument("password", SignUtils.md5("1").toLowerCase());
		httpSampler.setDomain(HOST_NAME);
		httpSampler.setPort(port);
		httpSampler.setPath("/api/v1/user/login");
		httpSampler.setMethod("POST");
		 
		HeaderManager headerManager = new HeaderManager();
		headerManager.setName(JMeterUtils.getResString("CAAS-HM"));
		headerManager.setProperty(TestElement.TEST_CLASS, HeaderManager.class.getName());
		headerManager.setProperty(TestElement.GUI_CLASS, HeaderPanel.class.getName());

		Header header = new Header();
		header.setName("Content-type");
		header.setValue("application/x-www-form-urlencoded");
		headerManager.add(header);
		// httpSampler.setHeaderManager(headerManager);

		// Loop Controller
		LoopController loopController = new LoopController();
		loopController.setLoops(100);
		loopController.setFirst(true);
		loopController.setProperty(TestElement.TEST_CLASS, LoopController.class.getName());
		loopController.setProperty(TestElement.GUI_CLASS, LoopControlPanel.class.getName());
		loopController.initialize();

		// Thread Group
		org.apache.jmeter.threads.ThreadGroup threadGroup = new org.apache.jmeter.threads.ThreadGroup();
		threadGroup.setName("CAAS-ThreadGroup");
		threadGroup.setNumThreads(10);
		threadGroup.setRampUp(1);
		threadGroup.setSamplerController(loopController);
		threadGroup.setProperty(TestElement.TEST_CLASS, ThreadGroup.class.getName());
		threadGroup.setProperty(TestElement.GUI_CLASS, ThreadGroupGui.class.getName());

		// Test Plan
		TestPlan testPlan = new TestPlan("TP-CAAS");
		testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
		testPlan.setProperty(TestElement.GUI_CLASS, TestPlanGui.class.getName());
		testPlan.setUserDefinedVariables((Arguments) new ArgumentsPanel().createTestElement());

		// Construct Test Plan from previously initialized elements
		HashTree requestHashTree = new HashTree();

		requestHashTree.add(httpSampler, headerManager);

		testPlanTree.add(resultCollector());
		//testPlanTree.add(testPlanTree.getArray()[0],resultCollector());
		HashTree threadGroupHashTree = testPlanTree.add(testPlan, threadGroup);

		threadGroupHashTree.add(requestHashTree);

		// Run Test Plan
		jmeter.configure(testPlanTree);
		jmeter.run();
		jmeter.exit();
	}

	public ResultCollector resultCollector() {
		// Result collector
		Summariser summer = null;
		String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");//$NON-NLS-1$
		if (summariserName.length() > 0) {
		    summer = new Summariser(summariserName);
		}

		String logFile ="temp.log";
		ResultCollector resultCollector = new ResultCollector(summer);
		resultCollector.setFilename(logFile);
	 		 
		SampleSaveConfiguration saveConfiguration = new SampleSaveConfiguration();
		saveConfiguration.setAsXml(true);
		saveConfiguration.setCode(true);
		saveConfiguration.setLatency(true);
		saveConfiguration.setTime(true);
		saveConfiguration.setTimestamp(true);
		resultCollector.setSaveConfig(saveConfiguration);
		return resultCollector;
	}
}
