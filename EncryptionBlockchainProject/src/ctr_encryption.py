from Crypto.Cipher import AES
from Crypto.Util import Counter
import os
import base64

def encrypt_ctr(plain_text, key):
    ctr = Counter.new(128)
    cipher = AES.new(key, AES.MODE_CTR, counter=ctr)
    cipher_text = cipher.encrypt(plain_text.encode())
    return base64.b64encode(cipher_text).decode('utf-8')

def decrypt_ctr(cipher_text, key):
    cipher_bytes = base64.b64decode(cipher_text)
    ctr = Counter.new(128)
    cipher = AES.new(key, AES.MODE_CTR, counter=ctr)
    plain_text = cipher.decrypt(cipher_bytes)
    return plain_text.decode('utf-8')

if __name__ == "__main__":
    key = os.urandom(16)  # AES-128 key
    transaction_data = "Transaction data to be encrypted"
    encrypted_data = encrypt_ctr(transaction_data, key)
    print("Encrypted (CTR):", encrypted_data)

    decrypted_data = decrypt_ctr(encrypted_data, key)
    print("Decrypted (CTR):", decrypted_data)
