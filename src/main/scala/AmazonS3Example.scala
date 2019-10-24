import com.amazonaws.auth.{AWSCredentialsProvider, AWSStaticCredentialsProvider, BasicAWSCredentials}
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.regions.{Region, RegionImpl, Regions}
import com.amazonaws.services.s3.{AmazonS3Client, AmazonS3ClientBuilder}

object AmazonS3Example {
  def main(args: Array[String]): Unit = {
    val accessKey = "tmp"
    val secretKey = "tmp"
    val bucketName = ""
    val urlPrefix = ""

    val credentials = new BasicAWSCredentials(accessKey, secretKey)
    val client = AmazonS3ClientBuilder.standard().withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
      urlPrefix,
      "tmp")).withCredentials(new AWSStaticCredentialsProvider(credentials)).build()
//    client.listBuckets()
    print(client.listObjects(bucketName).getObjectSummaries)
  }
}
