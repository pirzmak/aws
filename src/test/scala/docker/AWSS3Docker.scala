package docker

import java.io.{BufferedInputStream, File}
import java.net.URI
import java.nio.file.Paths

import com.spotify.docker.client.messages.{ContainerConfig, HostConfig, RegistryAuth}
import com.spotify.docker.client.{DefaultDockerClient, DockerClient}

trait AWSS3Docker {
  private val docker = DefaultDockerClient.builder().uri("unix://var/run/docker.sock").build()
  private val imageName = "awss3docker" + System.nanoTime()
  docker.build(Paths.get(""), imageName)

  val hostConfig = HostConfig.builder().build

  docker.pull(imageName, RegistryAuth.create(
    "",
    "",
    "not@use.it",
    "",
    null,
    null
  ))

  val containerConfig = ContainerConfig.builder()
    .hostConfig(hostConfig)
    .entrypoint()
    .image(imageName)
    .build

  val creation = docker.createContainer(containerConfig)

  docker.startContainer(creation.id)
}
