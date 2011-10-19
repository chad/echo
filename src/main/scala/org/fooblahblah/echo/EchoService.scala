package org.fooblahblah.echo

import blueeyes.BlueEyesServiceBuilder
import blueeyes.concurrent.Future._
import blueeyes.core.data.BijectionsChunkByteArray
import blueeyes.core.data.BijectionsChunkJson
import blueeyes.core.data.ByteChunk
import blueeyes.core.http.HttpHeaders._
import blueeyes.core.http.HttpStatusCodes._
import blueeyes.core.http.MimeTypes._
import blueeyes.core.http.HttpException
import blueeyes.core.http.HttpRequest
import blueeyes.core.http.HttpResponse
import blueeyes.core.http.HttpStatus
import blueeyes.core.http.combinators.HttpRequestCombinators
import blueeyes.core.service.RestPathPatternImplicits._
import blueeyes.health.HealthMonitor
import blueeyes.core.service.HttpRequestHandlerCombinators

trait EchoService extends BlueEyesServiceBuilder with HttpRequestHandlerCombinators with HttpRequestCombinators 
  with BijectionsChunkByteArray with BijectionsChunkJson {
    
  val echoService = service("echo", "1.0.0") {
    healthMonitor { monitor =>
      requestLogging { context =>
        request {
          path("/echo") {
	    post { request: HttpRequest[ByteChunk] =>
              HttpResponse[ByteChunk](content = request.content).future
            }
          }
        }
      }
    }
  }
}

import blueeyes.BlueEyesServer

object EchoServer extends BlueEyesServer with EchoService
