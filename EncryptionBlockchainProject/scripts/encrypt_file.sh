#!/bin/bash
# Encrypt a file using OpenSSL

# Check if the plaintext file exists
if [ ! -f "../data/plaintext.txt" ]; then
    echo "Plaintext file not found!"
    exit 1
fi

# Encrypt the plaintext file using PBKDF2 for better security
openssl enc -aes-128-cbc -salt -in ../data/plaintext.txt -out ../data/encrypted.txt -k yourpassword -pbkdf2

echo "File encrypted successfully: ../data/encrypted.txt"
