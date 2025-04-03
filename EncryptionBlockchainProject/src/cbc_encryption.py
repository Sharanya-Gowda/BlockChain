from Crypto.Cipher import AES
from Crypto.Util.Padding import pad, unpad
import os
import base64

def encrypt_cbc(plain_text, key):
    cipher = AES.new(key, AES.MODE_CBC)
    iv = cipher.iv
    cipher_text = cipher.encrypt(pad(plain_text.encode(), AES.block_size))
    return base64.b64encode(iv + cipher_text).decode('utf-8')

def decrypt_cbc(cipher_text, key):
    cipher_bytes = base64.b64decode(cipher_text)
    iv = cipher_bytes[:AES.block_size]
    cipher = AES.new(key, AES.MODE_CBC, iv)
    plain_text = unpad(cipher.decrypt(cipher_bytes[AES.block_size:]), AES.block_size)
    return plain_text.decode('utf-8')

if __name__ == "__main__":
    key = os.urandom(16)  # AES-128 key
    transaction_data = "Transaction data to be encrypted"
    encrypted_data = encrypt_cbc(transaction_data, key)
    print("Encrypted (CBC):", encrypted_data)

    decrypted_data = decrypt_cbc(encrypted_data, key)
    print("Decrypted (CBC):", decrypted_data)
