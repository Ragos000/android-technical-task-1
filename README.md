# The Solution:

The solution is written in Kotlin.
There are multiple libraries I had to use to fulfill the task.
These libraries were helpful in the Restful communiation.
Libs:
	//gson
    implementation 'com.google.code.gson:gson:2.8.5'
    //okhttp
    implementation 'com.squareup.okhttp3:okhttp:3.10.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:3.9.0'
    //retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.3.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.3.0'
    implementation 'com.squareup.retrofit2:converter-scalars:2.3.0'
    //rxandroid
    implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'
    implementation 'io.reactivex.rxjava2:rxjava:2.1.7'

## Part A - Fix current bugs

### Bug 1

I only had to adjust the layout xml a little bit. There were some constraints missing.

### Bug 2

I had to rearrange the structure of the code in the allFieldsValid method and inverted the logic.

### Bug 3

I had to create two separate animation. The fisrt is only animating once but the second one is an infinite loop.

## Part B - Add 2 new screens

This part was much harder than I expected compared to Part A.
It was challenging especially because I took the opportunity to use Kotlin though my main language is Java.
I had to use some libraries to accelerate my work.
The project has a package which contains the classes for the RestfulApi communication.
There is a dto package which contains the data classes for the server responses.
There is a package for the two additional fragments/screens.
There is a model package which contains a single UserPlan data class.
And there is an adapter package for my custom adapter and a ItemDecoration class required for the Recyclerview on the second screen.