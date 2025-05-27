import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

class BuildConfigPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        setProjectConfig(project)
    }

    private fun setProjectConfig(project: Project) {
        val libs: VersionCatalog = project.extensions.getByType<VersionCatalogsExtension>().named("libs")
        val versionName = libs.findVersion("version.name").get().requiredVersion
        val versionCode = libs.findVersion("version.code").get().requiredVersion.toInt()

        project.android().apply {

            flavorDimensions(
                "environment"
            )

            productFlavors.create("develop").apply {
                dimension = "environment"
                buildConfigField("Integer", "VERSION_CODE", "$versionCode")
                buildConfigField("String", "VERSION_NAME", "\"$versionName\"")
                buildConfigField("String", "BASE_URL", "\"https://api.thecatapi.com/\"")
                buildConfigField("String", "API_KEY", "\"live_Sf7l2YK65VDVAkSVkxo68pRLnpcJZ5QiABqpnxibHtEjNiB669lJniJ2Gjli227q\"")
                resValue("string", "app_name", "Cat Hashtad")
            }

            productFlavors.create("production").apply {
                dimension = "environment"
                buildConfigField("Integer", "VERSION_CODE", "$versionCode")
                buildConfigField("String", "VERSION_NAME", "\"$versionName\"")
                buildConfigField("String", "BASE_URL", "\"https://api.thecatapi.com/\"")
                buildConfigField("String", "API_KEY", "\"live_Sf7l2YK65VDVAkSVkxo68pRLnpcJZ5QiABqpnxibHtEjNiB669lJniJ2Gjli227q\"")
                resValue("string", "app_name", "Cat Hashtad")
            }
        }
    }

    private fun Project.android(): BaseExtension {
        return extensions.findByName("android") as? BaseExtension ?: error("Not an Android module: $name")
    }
}