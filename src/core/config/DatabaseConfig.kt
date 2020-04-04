package core.config

data class DatabaseConfig(
    val host: String,
    val port: Int,
    val databaseName: String,
    val user: String,
    val password: String
)