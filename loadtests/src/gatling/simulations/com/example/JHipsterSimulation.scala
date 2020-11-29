package com.example

import io.gatling.core.Predef.{exec, _}
import io.gatling.http.Predef._

import scala.concurrent.duration._

class JHipsterSimulation extends Simulation {
  val httpConf = http
    .baseUrl(baseUrl)
    .header("Content-Type", "application/json")

  def userCount: Int = getProperty("loadtests.users", "1000").toInt
  def baseUrl: String = getProperty("loadtests.baseurl", "http://localhost:8080")
  def rampDuration: Int = getProperty("loadtests.rampduration", "10").toInt
  def testDuration: Int = getProperty("loadtests.duration", "60").toInt

  /** * Before ***/
  before {
    println(s"baseUrl: ${baseUrl}")
    println(s"Running test with ${userCount} users")
    println(s"Ramping users over ${rampDuration} seconds")
    println(s"Maximum test duration: ${testDuration} seconds")
  }

  /** * Helper Methods ***/
  protected def getProperty(propertyName: String, defaultValue: String) = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }

  def postMessage() = {
    exec(http("postMessage")
      .post("/messages")
      .body(StringBody("{\"subscriberId\": 1,\"from\": \"+12063333333\",\"to\": \"+12064444444\",\"body\": \"Hello, world!\",\"created\": 1604270559.513000000}"))
      .check(status.is(200)))
  }

  /** * Scenario Design ***/
  val scn = scenario("JHipster Load Test")
    .forever() {
      exec(postMessage())
    }

  /** * Setup Load Simulation ***/
  setUp(
    scn.inject(
      nothingFor(5 seconds),
      rampUsers(userCount).during(rampDuration seconds))
  ).protocols(httpConf).maxDuration(testDuration seconds)

  /** * After ***/
  after {
    println("Stress test completed")
  }
}
