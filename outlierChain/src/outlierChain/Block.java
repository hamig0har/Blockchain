package outlierChain;

import java.util.Date;

public class Block {
	
	public String hash;
	public String previousHash;
	private String data;//simple string data but this can be anything
	private long timeStamp;//as number of milliseconds since 1/1/1970.
	private int nonce;
	
	
	public Block(String data, String previousHash) {
		this.data = data;
		this .previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash(); //Making sure we do this after we set the other values.
	}
	
	public String calculateHash() {
		String calculatedhash = stringUtil.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				data 
				);
		return calculatedhash;
	}
	
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}

}
