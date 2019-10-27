import docker.AWSS3Docker
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

class ScalaSpec extends FlatSpec with Matchers with BeforeAndAfterAll with AWSS3Docker {
  override def beforeAll() {
    initDocker("localstack/localstack")
  }

  override def afterAll() {
    killDocker
  }
}
