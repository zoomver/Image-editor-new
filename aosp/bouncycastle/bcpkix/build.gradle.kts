// Copyright 2023 yuyezhong@gmail.com
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":aosp:bouncycastle:bcprov"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks {
    jar {
        from(configurations.runtimeClasspath.get().map({ if (it.isDirectory) it else zipTree(it) }))
        excludes.addAll(mutableSetOf("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA"))
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        dependsOn(":aosp:bouncycastle:bcprov:jar")
    }
}
