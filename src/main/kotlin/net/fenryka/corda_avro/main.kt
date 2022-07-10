package net.fenryka.corda_avro

import org.apache.avro.Schema
import org.apache.avro.SchemaNormalization

fun main(){
    println ("Hello world");
    val schema = Schema.Parser().parse(
        ClassLoader.getSystemClassLoader().getResourceAsStream("Envelope.avsc")
    )

    val fingerprint = SchemaNormalization.parsingFingerprint("SHA-256", schema);
    println (schema);
    println ("---")
    println (SchemaNormalization.toParsingForm(schema))
    println (fingerprint);
}