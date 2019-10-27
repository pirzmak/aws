package docker

import java.io.{BufferedInputStream, File}
import java.net.URI
import java.nio.file.Paths

import com.spotify.docker.client.messages.{ContainerConfig, ContainerCreation, HostConfig, PortBinding, RegistryAuth}
import com.spotify.docker.client.{DefaultDockerClient, DockerClient}

import scala.jdk.CollectionConverters._

trait AWSS3Docker {
  private val dockerClient = DefaultDockerClient.fromEnv().build()
  private var dockerContainer: Option[ContainerCreation] = None

  def initDocker(imageName: String,
                 portBindings: Map[String, List[(String, String)]] = Map("4567-4584/tcp" -> List(("0.0.0.0", "4567-4584")))) = {
    val JPortBindings: java.util.Map[String, java.util.List[PortBinding]] =
      portBindings.view.mapValues(_.map(p => PortBinding.of(p._1, p._2)).asJava).toMap.asJava

    val hostConfig = HostConfig.builder().portBindings(JPortBindings).build

    val env: java.util.List[String] = List("DOCKER_HOST=unix:///var/run/docker.sock", "SERVICES=s3").asJava
    val containerConfig = ContainerConfig.builder()
      .hostConfig(hostConfig)
      .image(imageName)
      .env(env)
      .build

    dockerContainer = Some(dockerClient.createContainer(containerConfig))

    dockerClient.startContainer(dockerContainer.get.id)
  }

  def killDocker: Unit = {
    dockerContainer.foreach(c => dockerClient.killContainer(c.id))
  }
}
