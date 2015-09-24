package cn.wltc.framework.util.thread;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

import cn.wltc.framework.util.properties.PropertiesUtil;

public class ThreadPool {
	public static long _next_task_id = 0;
	public int _cur_thread_num = 0;
	public List<Task> activeTaskQueue = Collections
			.synchronizedList(new LinkedList<Task>());
	//public List<Task> inactiveTaskQueue = Collections.synchronizedList(new LinkedList<Task>());
	public ConcurrentMap<String, Task> taskMap = new ConcurrentHashMap<String, Task>();
	private WorkThread[] threads;

	private static ThreadPool _instance = null;

	private static Logger log = Logger.getLogger(ThreadPool.class);

	public ThreadPool() {
		_cur_thread_num = Integer.valueOf(PropertiesUtil.getProperty(
				"default.thread.number").toString());
		threads = new WorkThread[_cur_thread_num];
		for (int i = 0; i < _cur_thread_num; ++i) {
			threads[i] = new WorkThread();
		}
	}

	public static ThreadPool getInstance() {
		if (_instance == null) {
			synchronized (ThreadPool.class) {
				if (_instance == null) {
					_instance = new ThreadPool();
				}
			}
		}
		return _instance;
	}

	public static long generateTaskId() {
		_next_task_id += (_next_task_id + 1) / 1000000;
		if (_next_task_id == 0)
			_next_task_id++;
		return _next_task_id;
	}

	public void start() {
		for (int i = 0; i < _cur_thread_num; ++i) {
			if(!threads[i].isAlive()){
			   threads[i].start();
			}
		}
		log.info(String.format("thread pool init," + _cur_thread_num
				+ " threads!"));
	}
	
	public void stop() {
	  try{
			for (int i = 0; i < _cur_thread_num; ++i) {
				threads[i].join();
			}
		 }
	    catch(InterruptedException ex){
			 log.error(ex);
         }
		log.info(String.format("thread pool stop" ));
	}

	public void addActiveTask(Task new_task) {

		synchronized (activeTaskQueue) {
			if (new_task != null) {
				activeTaskQueue.add(new_task);
				//inactiveTaskQueue.remove(new_task);
				activeTaskQueue.notifyAll();

			}
		}
	}

	/**
	 * 批量增加可执行task
	 * @param taskList
	 */
	public void addActiveTaskList(List<Task> taskList) {

		synchronized (activeTaskQueue) {
			for (Task task : taskList)
				if (task != null) {
					activeTaskQueue.add(task);
					activeTaskQueue.remove(task);
				}
			activeTaskQueue.notifyAll();
		}
	}

//	public void addInactiveTask(Task new_task) {
//		synchronized (inactiveTaskQueue) {
//			if (new_task != null) {
//				inactiveTaskQueue.add(new_task);
//				activeTaskQueue.remove(new_task);
//				inactiveTaskQueue.notifyAll();
//			}
//		}
//	}

	public int getActiveTaskCount() {
		return activeTaskQueue.size();
	}

//	public int getInactiveTaskCount() {
//		return inactiveTaskQueue.size();
//	}

	public Task getActiveTask() {
		Task r = null;
		synchronized (activeTaskQueue) {
			while (activeTaskQueue.isEmpty()) {
				try {
					activeTaskQueue.wait();
				} catch (InterruptedException e) {
					log.error(e);
				}
			}

			r = (Task) activeTaskQueue.remove(0);
			return r;
		}
	}

//	public Task getInactiveTask() {
//		Task r = null;
//		synchronized (inactiveTaskQueue) {
//			while (inactiveTaskQueue.isEmpty()) {
//				try {
//					inactiveTaskQueue.wait();
//				} catch (InterruptedException e) {
//					log.error(e);
//				}
//			}
//
//			r = (Task) inactiveTaskQueue.remove(0);
//			return r;
//		}
//	}

//	public void removeInactiveTask(Task task) {
//		synchronized (inactiveTaskQueue) {
//			inactiveTaskQueue.remove(task);
//
//		}
//	}
//
//	public void removeActiveTask(Task task) {
//		synchronized (activeTaskQueue) {
//			activeTaskQueue.remove(task);
//		}
//	}
	
	

	/**
	 * task存储，方便计算总task数量与获取
	 * 
	 * @param key
	 * @param task
	 */
	public void setTaskMap(String key, Task task) {
		taskMap.put(key, task);
	}

	public int getTaskCount() {
		return taskMap.size();
	}

	public Task getTask(String key) {
		return taskMap.get(key);
	}

//	public boolean isTaskInactive(Task task) {
//		return inactiveTaskQueue.contains(task);
//	}

	public boolean containsTaskKey(String key) {
		return taskMap.containsKey(key);
	}

}