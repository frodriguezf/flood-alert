
class bcolors:
    HEADER = '\033[95m'
    OKBLUE = '\033[94m'
    OKGREEN = '\033[92m'
    WARNING = '\033[93m'
    FAIL = '\033[91m'
    ENDC = '\033[0m'
    BOLD = '\033[1m'
    UNDERLINE = '\033[4m'


def cprint(color, msg):
    print(color + msg + bcolors.ENDC)


def green(msg):
    cprint(bcolors.OKGREEN, msg)


def blue(msg):
    cprint(bcolors.OKBLUE, msg)


def yellow(msg):
    cprint(bcolors.WARNING, msg)


def red(msg):
    cprint(bcolors.FAIL, msg)
