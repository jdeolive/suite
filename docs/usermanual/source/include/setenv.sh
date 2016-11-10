#! /bin/sh
export JAVA_OPTS="$JAVA_OPTS -Xms=256m -Xmx=756m"
export JAVA_OPTS="$JAVA_OPTS -XX:SoftRefLRUPolicyMSPerMB=36000"
export JAVA_OPTS="$JAVA_OPTS -XX:-UsePerfData"

# marlin-renderer
export JAVA_OPTS="$JAVA_OPTS -Xbootclasspath/a:/usr/share/tomcat8/bin/marlin-0.7.3-Unsafe.jar"
export JAVA_OPTS="$JAVA_OPTS -Dsun.java2d.renderer=org.marlin.pisces.PiscesRenderingEngine"
export JAVA_OPTS="$JAVA_OPTS -Dsun.java2d.renderer.useThreadLocal=false"

# geoserver settings
export JAVA_OPTS="$JAVA_OPTS -Dorg.geotools.referencing.forceXY=true"
export JAVA_OPTS="$JAVA_OPTS -Dorg.geotoools.render.lite.scale.unitCompensation=true"