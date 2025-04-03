from Crypto.Cipher import AES
from Crypto.Util.Padding import pad, unpad
from Crypto.Util import Counter
import os
import base64

def encrypt_aes_cbc(plain_text, key):
    """Encrypts plaintext using AES in CBC mode."""
    cipher = AES.new(key, AES.MODE_CBC)
    iv = cipher.iv
    cipher_text = cipher.encrypt(pad(plain_text.encode(), AES.block_size))
    return base64.b64encode(iv + cipher_text).decode('utf-8')

def decrypt_aes_cbc(cipher_text, key):
    """Decrypts ciphertext using AES in CBC mode."""
    cipher_bytes = base64.b64decode(cipher_text)
    iv = cipher_bytes[:AES.block_size]
    cipher = AES.new(key, AES.MODE_CBC, iv)
    plain_text = unpad(cipher.decrypt(cipher_bytes[AES.block_size:]), AES.block_size)
    return plain_text.decode('utf-8')

def encrypt_aes_ctr(plain_text, key):
    """Encrypts plaintext using AES in CTR mode."""
    ctr = Counter.new(128)
    cipher = AES.new(key, AES.MODE_CTR, counter=ctr)
    cipher_text = cipher.encrypt(plain_text.encode())
    return base64.b64encode(cipher_text).decode('utf-8')

def decrypt_aes_ctr(cipher_text, key):
    """Decrypts ciphertext using AES in CTR mode."""
    cipher_bytes = base64.b64decode(cipher_text)
    ctr = Counter.new(128)
    cipher = AES.new(key, AES.MODE_CTR, counter=ctr)
    plain_text = cipher.decrypt(cipher_bytes)
    return plain_text.decode('utf-8')

if __name__ == "__main__":
    key = os.urandom(16)  # AES-128 key
    transaction_data = "Transaction data to be encrypted"

    # CBC Mode
    encrypted_data_cbc = encrypt_aes_cbc(transaction_data, key)
    print("Encrypted (CBC):", encrypted_data_cbc)
    decrypted_data_cbc = decrypt_aes_cbc(encrypted_data_cbc, key)
    print("Decrypted (CBC):", decrypted_data_cbc)

    # CTR Mode
    encrypted_data_ctr = encrypt_aes_ctr(transaction_data, key)
    print("Encrypted (CTR):", encrypted_data_ctr)
    decrypted_data_ctr = decrypt_aes_ctr(encrypted_data_ctr, key)
    print("Decrypted (CTR):", decrypted_data_ctr)
