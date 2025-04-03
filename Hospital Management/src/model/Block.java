package model;

import java.security.MessageDigest;

public class Block {
    private String patientId;
    private String data;
    private String hash;
    private String previousHash;

    public Block(String patientId, String data, String previousHash) {
        this.patientId = patientId;
        this.data = data;
        this.previousHash = previousHash;
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String input = patientId + data + previousHash;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getHash() { return hash; }
    public String getPreviousHash() { return previousHash; }
    public String getPatientId() { return patientId; }
    public String getData() { return data; }
}

