# PublicUtils
杨林的工具类库——mvp架构和rxjava


用于个人的网络框架封装  和  mvp架构集成

关于使用

project中的build.gradle文件添加
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
}

app中的build.gradle文件添加

dependencies {
	  implementation 'com.github.15011229708:PublicUtils:1.0'
}
