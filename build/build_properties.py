# parses the build.properties file making it available to sphinx config files
import os
from StringIO import StringIO
from ConfigParser import ConfigParser

__all__ = ['suite_version']

bpfile = open(os.path.join(os.path.dirname(__file__), 'build.properties'))
buf = StringIO()
buf.write('[build]')
buf.write(bpfile.read())
buf.seek(0, os.SEEK_SET)

cp = ConfigParser()
cp.readfp(buf)

suite_version = cp.get('build', 'suite.version')
