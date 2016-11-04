name := "big-data-labs"

organization := "com.infobarbosa"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.8"

val sparkVersion = "2.0.1"

resolvers += "Spark Packages Repo" at "https://dl.bintray.com/spark-packages/maven"

lazy val root = (project in file(".")).
  settings(
    name := "big-data-labs",
    version := "1.0",
    scalaVersion := "2.11.8",
    mainClass in Compile := Some("com.infobarbosa.spark.BolsaFamiliaCarregaCassandra")        
  )

libraryDependencies ++= Seq(
	"org.apache.spark" %% "spark-core" % sparkVersion % "provided",
	"org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
	"datastax" % "spark-cassandra-connector" % "2.0.0-M2-s_2.11",
	"org.scala-lang" % "scala-compiler" % scalaVersion.value
)

// META-INF discarding
mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
   {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case x => MergeStrategy.first
   }
}
