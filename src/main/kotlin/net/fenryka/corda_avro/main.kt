package net.fenryka.corda_avro

import org.apache.avro.Schema
import org.apache.avro.SchemaNormalization

fun schema (outer: String, files_: List<String> = listOf()) {
    val parser = Schema.Parser();
    files_.forEach { file ->
        parser.parse(
            ClassLoader.getSystemClassLoader().getResourceAsStream (file)
        )
    }

    val schema = parser.parse(
        ClassLoader.getSystemClassLoader().getResourceAsStream (outer)
    )


    val fingerprint = SchemaNormalization.parsingFingerprint("SHA-256", schema)

    println ("$outer::$fingerprint")
}

fun main(){
    schema ("Envelope.avsc")
    schema ("HoldingIdentity.avsc")
    schema ("UnauthenticatedMessageHeader.avsc", listOf ("HoldingIdentity.avsc"))
    schema ("UnauthenticatedMessage.avsc", listOf ("HoldingIdentity.avsc", "UnauthenticatedMessageHeader.avsc"))
}