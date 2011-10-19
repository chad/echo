package org.fooblahblah.echo

import blueeyes.core.data.{ ByteChunk, BijectionsChunkJson, BijectionsChunkString, BijectionsChunkByteArray }
import blueeyes.core.http.HttpStatusCodes._
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
    }
    """

  "Echo service" should {

    "return posted content as response" in {
      val expected = "Foo"
      val response = service.post[String]("/echo")(expected)
      response.value must eventually(beSomething)
      response.value.get.status.code must beEqual(OK)
      response.value.get.content must beSome(expected)
    }

  }
}
