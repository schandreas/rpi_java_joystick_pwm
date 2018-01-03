# rpi_java_joystick_pwm
Program to read input from a Controller and steer a little robot


## running
1. Download the repo
2. go to the librumble_src folder and `make` it. It will copy itself automatically into /usr/lib/librumble.so
3. Build the Java Application (Project is in Eclipse format already so that is recommended)
4. Connect Controller (Either DS4 or Xbox 360 wired/wireless) (Help for DS4: https://wiki.gentoo.org/wiki/Sony_DualShock works with raspbian aswell)
5. run enabledrivers.sh
6. Run the java application with sudo
