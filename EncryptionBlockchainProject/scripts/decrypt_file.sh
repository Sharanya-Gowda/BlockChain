#!/bin/bash
# Decrypt a file using OpenSSL

# Check if the encrypted file exists
if [ ! -f "../data/encrypted.txt" ]; then
    echo "Encrypted file not found!"
    exit 1
fi

# Decrypt the encrypted file using PBKDF2
openssl enc -d -aes-128-cbc -in ../data/encrypted.txt -out ../data/decrypted.txt -k yourpassword -pbkdf2

echo "File decrypted successfully: ../data/decrypted.txt"
