package cn.wltc.framework.util.thread;

public abstract class Task implements Runnable {
    public String _taskID = "";
    public boolean _isFinish = true;
    public Task(){
        
    }
    
    public Task(String task_id){
        _taskID = task_id;
    }
    
    public void setTaskId(String task_id){
        _taskID = task_id;
    }
    
    public String getTaskId(){
        return _taskID;
    }
    

    public boolean isFinish() {
		return _isFinish;
	}

	public void setFinishStatus(boolean isFinish) {
		_isFinish = isFinish;
	}

	public abstract void run();
}
