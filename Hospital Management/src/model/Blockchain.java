package model;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> chain;

    public Blockchain() {
        chain = new ArrayList<>();
        // Create the genesis block
        chain.add(new Block("GENESIS", "Genesis Block", "0"));
    }

    public void addBlock(String patientId, String data) {
        String previousHash = chain.get(chain.size() - 1).getHash();
        Block newBlock = new Block(patientId, data, previousHash);
        chain.add(newBlock);
        System.out.println("✅ New block added: " + newBlock.getHash() + 
                           " | Patient ID: " + patientId + 
                           " | Data: " + data);
    }

    public List<Block> getChain() {
        return chain;
    }
}

