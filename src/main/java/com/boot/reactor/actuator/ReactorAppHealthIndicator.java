package com.boot.reactor.actuator;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ReactorAppHealthIndicator implements HealthIndicator{

	private static final Logger logger=Logger.getLogger(ReactorAppHealthIndicator.class.getName());
	
	@Override
	public Health health() {
		String hostAddress="0.0.0.0";
		int errorCode = check(hostAddress,8080); // perform some specific health check
		try {
		hostAddress=InetAddress.getLocalHost().getHostAddress().toString();
		} catch (UnknownHostException e) {
			logger.log(Level.SEVERE,e.getMessage());
		}
		if (errorCode != 0) {
			logger.log(Level.SEVERE,"down");
			return Health.outOfService().withDetail("Error Code", errorCode).build();
		}
		Map<String, Long> info=getJVMMemoryDetail();
		return Health.up()
				.withDetail("Server IP", hostAddress)
				.withDetail("Used-Heap-Size-Memory",info.get("Used-Heap-Size-Memory"))
				.withDetail("Free-Heap-Size-Memory",info.get("Free-Heap-Size-Memory"))
				.withDetail("Total-Heap-Size",info.get("Total-Heap-Size"))
				.withDetail("Maximum-Heap-Size-Memory",info.get("Maximum-Heap-Size-Memory"))
				.build();
	}

	private int check(String ip,Integer port) {
		if(hostAvailabilityCheck(ip, port)) {
			logger.log(Level.INFO,"UP");
		return 0;
		}
		return 1;
		
	}

	

	public static Map<String, Long> getJVMMemoryDetail(){
		    long MEGABYTE = 1024L * 1024L;
		    Runtime runtime = Runtime.getRuntime();
		 	//Get the jvm heap size.
	        long totalHeapSize = (Runtime.getRuntime().totalMemory())/ MEGABYTE;
	        long usedHeapSizeMemory=(runtime.totalMemory() - runtime.freeMemory()) / MEGABYTE;
	        long freeHeapSizeMemory=runtime.freeMemory() / MEGABYTE;
	        //get Maximum available memory
	        long maximumHeapSizememory=runtime.maxMemory() / MEGABYTE;
	        Map<String,Long>  heapSizeMap=new LinkedHashMap<String,Long>();
	        heapSizeMap.put("Used-Heap-Size-Memory",usedHeapSizeMemory);
	        heapSizeMap.put("Free-Heap-Size-Memory", freeHeapSizeMemory);
	        heapSizeMap.put("Total-Heap-Size", totalHeapSize);
	        heapSizeMap.put("Maximum-Heap-Size-Memory", maximumHeapSizememory);
	            
	return heapSizeMap;
		
	}
	
	
	@SuppressWarnings("unused")
	private static List<Long> getSimpleJVMMemoryDetail(){
		long MEGABYTE = 1024L * 1024L;
		 Runtime runtime = Runtime.getRuntime();
		 	//Get the jvm heap size.
	        long totalHeapSize = (Runtime.getRuntime().totalMemory())/ MEGABYTE;
	        long usedHeapSizeMemory=(runtime.totalMemory() - runtime.freeMemory()) / MEGABYTE;
	        long freeHeapSizeMemory=runtime.freeMemory() / MEGABYTE;
	        //get Maximum available memory
	        long maximumHeapSizememory=runtime.maxMemory() / MEGABYTE;
	        List<Long> list=new ArrayList<Long>();	        
	        list.add(usedHeapSizeMemory);
	        list.add(freeHeapSizeMemory);
	        list.add(totalHeapSize);
	        list.add(maximumHeapSizememory);
		return list;
		
	}
	
	@SuppressWarnings("unused")
	private static boolean isDatabaseOnline(String address, int port) {
	    boolean result;

	    try {
	        Socket socket = new Socket(address, port);
	        socket.close();

	        result = true;
	    } catch (IOException e) {
	        result = false;
	    }

	    return result;
	}
	
	private static boolean hostAvailabilityCheck(String address, int port) {
	    boolean result;

	    try {
	        Socket socket = new Socket(address, port);
	        socket.close();
	        result = true;
	    } catch (IOException e) {
	        result = false;
	    }

	    return result;
	}
}
