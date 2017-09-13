/**
 * 
 */
package com.wangexp.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.wangexp.services.RemoteServiceP1;
import com.wangexp.services.RemoteServiceP2;
import com.wangexp.services.RemoteServiceP3;

/**
 * @author wangshuguang
 *
 */
@RestController
public class ConsumeResourceImpl implements ConsumeResource {
	@Autowired
	private RemoteServiceP1 servicep1;
	@Autowired
	private RemoteServiceP2 servicep2;
	@Autowired
	private RemoteServiceP3 servicep3;

	public String p1() {
		return servicep1.home();
	}

	public List<String> getAllServicesP1() {
		return servicep1.getAllServices();
	}

	public String p2() {
		return servicep2.home();
	}

	public List<String> getAllServicesP2() {
		return servicep2.getAllServices();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangexp.resources.ConsumeResource#p2hc()
	 */
	@Override
	public String p2hc() {
		return servicep2.hc();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangexp.resources.ConsumeResource#p22p3()
	 */
	@Override
	public String p22p3() {
		return servicep2.callp3();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangexp.resources.ConsumeResource#p3()
	 */
	@Override
	public String p3() {

		return servicep3.home();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangexp.resources.ConsumeResource#p1hcp3()
	 */
	@Override
	public String p1hcp3() {

		return servicep1.hc();
	}

}
