package Scenes.SceneHandler;

import DigitalSTROMDevices.DigitalSTROMDeviceStatusManager;
import Scenes.Scene.InternalScene;


public class SceneHandler implements SceneStatusListener{

	//private NamedScene scene;
	
	private boolean isActive = false;
	private final String ID;
	private InternalScene scene = null;
	private DigitalSTROMDeviceStatusManager devStatMan;
	
	public SceneHandler(String id){
		this.ID = id;
	}
	
	public void addDevStatMan(DigitalSTROMDeviceStatusManager devStatMan){
		this.devStatMan = devStatMan;
		this.devStatMan.registerSceneListener(this);
	}
	
	@Override
	public void onSceneStateChanged(boolean flag) {
		// TODO Auto-generated method stub
		this.isActive = flag;
	}

	@Override
	public void onSceneRemoved(InternalScene scene) {
		// TODO Auto-generated method stub
		this.scene = null;
	}

	@Override
	public void onSceneAdded(InternalScene scene) {
		this.scene = scene;
		this.isActive = scene.isActive();
	}

	public void activate(){
		devStatMan.sendSceneComandsToDSS(scene, true);
	}
	
	public boolean isActiv(){
		return this.isActive;
	}

	@Override
	public String getID() {
		return ID;
	}

}
