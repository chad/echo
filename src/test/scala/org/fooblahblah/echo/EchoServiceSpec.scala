package org.fooblahblah.echo

import blueeyes.core.data.{ ByteChunk, BijectionsChunkJson, BijectionsChunkString, BijectionsChunkByteArray }
import blueeyes.core.http.HttpHeaders._
import blueeyes.core.http.HttpStatusCodes._
import blueeyes.core.http.MimeTypes._
import blueeyes.core.service.RestPathPatternImplicits._
import blueeyes.core.service.test.BlueEyesServiceSpecification


object EchoServiceSpec extends BlueEyesServiceSpecification with EchoService with BijectionsChunkString {

  override val configuration = """
    services {
      echo {
        v1 {
        }
      }
    }

    log {
      level = "debug"
      console = true

      filterXLightWeb  {
        node = "org.xlightweb"
        level = "info"
      }
      filterXSocket  {
        node = "org.xsocket"
        level = "info"
      }
    }
    """

  "EchoService" should {

    "return posted content as response" in {
      val expected = "Foo"
      println("before post")
      val response = service.post[String]("/echo")(expected)
      println("response = " + response)
      response.value must eventually(beSomething)
      response.value.get.status.code must beEqual(OK)
    }

  }
}

