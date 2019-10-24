import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.{AmazonS3Client, AmazonS3ClientBuilder}

object AmazonS3Example {
  val accessKey = "AKIAJNSEFYYHD43JLEKQ"
  val secretKey = "8c0NOloloTrololoOloloTrololoOloloTrololo"
  val bucketName = "eaxme-test"
  val urlPrefix = "https://s3-us-west-1.amazonaws.com"

  val credentials = new BasicAWSCredentials(accessKey, secretKey)
  val client = new AmazonS3ClientBuilder(credentials)

  client.build().listObjects("test-bucket")
}
