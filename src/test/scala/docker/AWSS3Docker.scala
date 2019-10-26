package docker

import java.io.{BufferedInputStream, File}
import java.net.URI
import java.nio.file.Paths

import com.spotify.docker.client.messages.{ContainerConfig, HostConfig, RegistryAuth}
import com.spotify.docker.client.{DefaultDockerClient, DockerClient}
import scala.jdk.CollectionConverters._

trait AWSS3Docker {
  private val docker = DefaultDockerClient.fromEnv().build()
  private val imageName = "awss3docker" + System.nanoTime()
  docker.build(Paths.get("test/scala/docker").toAbsolutePath, imageName)

  val hostConfig = HostConfig.builder().build

  docker.pull(imageName, RegistryAuth.create(
    "",
    "",
    "not@use.it",
    "",
    null,
    null
  ))

  val env: java.util.List[String] = List("DOCKER_HOST=unix:///var/run/docker.sock", "SERVICES=s3").asJava
  val ports: java.util.List[String] = List("4567-4584:4567-4584").asJava

  val containerConfig = ContainerConfig.builder()
    .hostConfig(hostConfig)
    .entrypoint()
    .image(imageName)
    .portSpecs(ports)
    .env(env)
    .build

  val creation = docker.createContainer(containerConfig)

  docker.startContainer(creation.id)

  docker.listContainers()
}
