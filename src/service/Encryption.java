package service;

import java.util.HashMap;
import java.util.Map;

public class Encryption {
	public Map<String, Object> getEncryptionByte(byte[] encrypted){
		Map<String, Object> confidential=new HashMap<>();
		int key=(int) (Math.random()*999+1);
		byte[] dest=new byte[encrypted.length+1];
		for(int i=0;i<dest.length-1;i++){
			dest[i]=(byte) (encrypted[i]+key);
		}
		//把byte最后一位留给Key,以便解密
		dest[dest.length-1]=(byte) key;
		
		confidential.put("key", key);
		confidential.put("dest", dest);
		return confidential;
	}
	public byte[] getDecryptionByte(byte[] arr){
		byte[] dest=new byte[arr.length-1];
		int key=arr[arr.length-1];
		for(int i=0;i<dest.length;i++){
			dest[i]=(byte) (arr[i]-key);
		}
		return dest;
	}
}
