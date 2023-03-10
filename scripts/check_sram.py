import os
import mmap
import random
import binascii

CONST_length = (4 * 1024)
CONST_offset = 0x60000000

filep = os.open("/dev/mem", os.O_RDWR | os.O_SYNC)
mem = mmap.mmap(filep, CONST_length, mmap.MAP_SHARED,
		prot = mmap.PROT_READ | mmap.PROT_WRITE,
		offset = CONST_offset);

writeArray = bytearray(os.urandom(CONST_length))

mem.write(writeArray)
mem.seek(0)
readArray = mem.read()

if(writeArray == readArray):
	print("All good")
else:
	print("Not good")

