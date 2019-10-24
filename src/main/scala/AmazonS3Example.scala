import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.regions.{Region, RegionImpl, Regions}
import com.amazonaws.services.s3.{AmazonS3Client, AmazonS3ClientBuilder}

object AmazonS3Example {
  def main(args: Array[String]): Unit = {
    val accessKey = "AKIAJNSEFYYHD43JLEKQ"
    val secretKey = "8c0NOloloTrololoOloloTrololoOloloTrololo"
    val bucketName = "demo-bucket"
    val urlPrefix = "http://localhost:4572"

    val credentials = new BasicAWSCredentials(accessKey, secretKey)
    val client = AmazonS3ClientBuilder.standard().withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
      urlPrefix,
      "tmp")).build()

    print(client.listObjects(bucketName).toString)
  }
}
