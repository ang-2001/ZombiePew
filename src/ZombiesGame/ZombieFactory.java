
public class ZombieFactory {
	public createZombie getWave(String waveType){
	      if(waveType == null){
	         return null;
	      }		
	      if(waveType.equalsIgnoreCase("FirstWave")){
	         return new FirstWave();
	         
	      } else if(waveType.equalsIgnoreCase("SecondWave")){
	         return new SecondWave();
	      }
	      
	      return null;
	   }
}
