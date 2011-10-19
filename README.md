Echo service
============

The Echo service represents the smallest viable BlueEyes service along with a Spec (unit test) excercising the /echo path.  The project has an SBT example project definition defining a dependency on BlueEyes.

* Install sbt 0.11.x using brew or whatever package manager you use.
* Define an environment var, SBT_OPTS to override the permgen size.  Most folks put this in .bashrc:

        export SBT_OPTS="-XX:MaxPermSize=512m"
		
* To run the service use the command:

        $ sbt "run-main org.fooblahblah.echo.EchoServer --configFile config/echo.conf"

* The service should start on port 8080. To test issue a curl command POST'ing some content:

        $ curl --data-binary 'blah' http://localhost:8080/echo

* The service should reply with the same content.

