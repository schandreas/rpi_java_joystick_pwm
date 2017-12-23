#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <sys/ioctl.h>
#include <linux/input.h>
#include "rumble_jni.h"

/*
 * 
 */

int fd = 0;
struct ff_effect effect;
struct input_event play, gain;


JNIEXPORT void JNICALL Java_at_restental_andreas_joystick_rumble_RumbleControl_setuprumble
  (JNIEnv *env, jobject thisobject){
    if (fd == 0) {
        memset(&effect, 0, sizeof (effect));
        effect.type = FF_PERIODIC;
        effect.id = -1;
        effect.u.periodic.waveform = FF_SINE;
        effect.u.periodic.period = 100; /* 0.1 second */
        effect.u.periodic.magnitude = 0x7fff; /* 0.5 * Maximum magnitude */
        effect.u.periodic.offset = 0;
        effect.u.periodic.phase = 0;
        effect.direction = 0x4000; /* Along X axis */
        effect.u.periodic.envelope.attack_length = 1000;
        effect.u.periodic.envelope.attack_level = 0x7fff;
        effect.u.periodic.envelope.fade_length = 1000;
        effect.u.periodic.envelope.fade_level = 0x7fff;
        effect.trigger.button = 0;
        effect.trigger.interval = 0;
        effect.replay.length = 20000; /* 20 seconds */
        effect.replay.delay = 1000;

        fd = open("/dev/input/event0", O_RDWR);

        memset(&gain, 0, sizeof (gain));
        gain.type = EV_FF;
        gain.code = FF_GAIN;
        gain.value = 0xFFFF;
        write(fd, &gain, sizeof (gain));

        ioctl(fd, EVIOCSFF, &effect);

        memset(&play, 0, sizeof (play));
        play.type = EV_FF;
        play.code = effect.id;
    }
}

JNIEXPORT void JNICALL Java_at_restental_andreas_joystick_rumble_RumbleControl_startrumble
  (JNIEnv *env, jobject thisobject){
    if(play.value != 1){
        play.value = 1;
        write(fd, (const void*) &play, sizeof (play));
    }
}

JNIEXPORT void JNICALL Java_at_restental_andreas_joystick_rumble_RumbleControl_stoprumble
  (JNIEnv *env, jobject thisobject){
    if(play.value != 0){
        play.value = 0;
        write(fd, (const void*) &play, sizeof (play));
    }
}
