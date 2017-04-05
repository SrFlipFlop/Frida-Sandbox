#!/usr/bin/python
from __future__ import print_function, unicode_literals

import functools
import frida
import time
import sys
import os 

def on_message(message, data):
    if message['type'] == 'send':
        print('[+] {0}'.format(message['payload']))
    else:
        print('[-] {0}'.format(message['stack']))

def attach_script(session, script_name):
    with open(script_name,'r') as js_script:
        script = session.create_script(js_script.read())
        script.on('message', on_message)
        script.load()

def main(args):
    if len(args) < 2:
        print("[-] Usage: python frida-script.py app.package.name [/path/to/scripts/]")
        raise SystemExit

    frida_session = None
    while True:
        try:
            frida_session = frida.get_usb_device().attach(args[1])
            print("[+] Attached to: {0}".format(args[1]))
            break
        except:
            time.sleep(0.01)

    path = './'
    if len(args) > 2:
        path = args[2]        

    for root, dirs, files in os.walk(path):
        map(functools.partial(attach_script, frida_session), filter(lambda x: x.endswith('js'), files))

if __name__ == '__main__':
    main(sys.argv)
    sys.stdin.read()
