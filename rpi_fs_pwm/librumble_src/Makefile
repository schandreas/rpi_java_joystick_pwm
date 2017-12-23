default: librumble.o rumble_jni.h
	gcc -shared -o librumble.so librumble.o
	sudo cp librumble.so /usr/lib/librumble.so

rumble_jni.h:
	mv ./include/*.h rumble_jni.h

librumble.o: librumble.c rumble_jni.h
	gcc -c -fPIC -I/usr/lib/jvm/jdk-8-oracle-arm32-vfp-hflt/include/ -I/usr/lib/jvm/jdk-8-oracle-arm32-vfp-hflt/include/linux librumble.c

