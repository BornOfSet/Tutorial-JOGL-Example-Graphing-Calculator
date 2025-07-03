Run Configurations:
添加VM arguments:
--add-exports java.base/java.lang=ALL-UNNAMED
--add-exports java.desktop/sun.awt=ALL-UNNAMED
--add-exports java.desktop/sun.java2d=ALL-UNNAMED
 -Dsun.awt.noerasebackground=true



 
右键你的项目-> Build Path ->Configure Build Path-> Add JARs，添加你的Jar包，Jar包包括Assimp for java,glue,jogl,glm等
assimp本程序没有用到，里面包括AiScene，AiMesh等用来导入模型的类
glue忘了是干啥的了，好像我一直都没用到过，我也不清楚
jogl包括GLCanvas，GLEventListener，GLAutoDrawable，FPSAnimator等核心类，这些就是Jogl的本体。程序是跑在GLEventListener里面的，每帧都会调用display方法，而init类似构造函数，reshape是当你重新设置视口宽高的时候调用的
glm包括在assimp里面。glm里面是各种向量类型，比如向量的normalize,向量之间的加减法，向量的哈希都包括在这里面



RenderDoc使用教程：
首先把你的java程序导出，右键项目->export->选择runable jar file->选一个正确的Configuration（就是说你别导错文件了。你打开的文件，你右键的项目，不一定是你导出的文件。你要在Configurations里面说一下你到底要运行的是哪个Main），再选一个路径，一路点确定
写一个bat文件，格式如下：
java --add-exports java.base/java.lang=ALL-UNNAMED  --add-exports java.desktop/sun.awt=ALL-UNNAMED  --add-exports java.desktop/sun.java2d=ALL-UNNAMED  -Dsun.awt.noerasebackground=true -jar testjar2.jar
然后点击这个bat文件，你发现运行不起来。因为着色器错误。你需要复制着色器文件到jar文件所在的目录下
然后就没有问题了
接下来我说一下怎么用renderdoc调试jogl
Executable path: C:\Program Files\Java\jdk-17.0.2\bin\java.exe
Working Directory：C:/Users/User/Documents/customname
Command-line Arguments : --add-exports java.base/java.lang=ALL-UNNAMED  --add-exports java.desktop/sun.awt=ALL-UNNAMED  --add-exports java.desktop/sun.java2d=ALL-UNNAMED -Dsun.awt.noerasebackground=true -jar app.jar
然后找到Queue Capture，勾上，选Frame1，就可以调试了。
RenderDoc可以看像素的值，比如光线追踪的时候你就可以用renderdoc调试加速结构
而且它可以实时修改着色器文件，程序会马上响应的，按照你改完的着色器重新渲染一遍，很好用
注意，Executable path必须按我写的来，不然会有bug。虽然你把它换成bat文件，然后命令行留空（因为已经在bat文件里面写过了），也能运行程序，但是不知道为什么捕获不到屏幕

Nsight使用教程：
点Start activity
Appilication Executable: C:/Program Files/Java/jdk-17.0.2/bin/java.exe
Working directory : C:/Users/User/Documents/renderdoceclipse
Commandline argument : --add-exports java.base/java.lang=ALL-UNNAMED  --add-exports java.desktop/sun.awt=ALL-UNNAMED  --add-exports java.desktop/sun.java2d=ALL-UNNAMED  -Dsun.awt.noerasebackground=true -jar testjar.jar
Environment:空
Automatically connect:Yes

然后屏幕上会说：
Preparing to launch the Frame Debugger activity on localhost...
Launched process: java.exe (pid: 16184)
C:/Program Files/Java/jdk-17.0.2/bin/java.exe --add-exports java.base/java.lang=ALL-UNNAMED --add-exports java.desktop/sun.awt=ALL-UNNAMED --add-exports java.desktop/sun.java2d=ALL-UNNAMED -Dsun.awt.noerasebackground=true -jar CurveCalculator.jar
Attempting to automatically connect...
Searching for attachable processes on localhost:49152-49215...
Attachable process detected: java.exe

然后，要是软件界面里还是什么都没有，那是因为有个很搞笑的bug，我设计的时候是没有写FPS animator的，所以它只会运行一帧，然后就冻结了
你把窗口放大，这样它就会重新刷新画布，这个时候Nsight就能捕捉到了
然后会多出来一个视口，这个视口里面你可以逐个绘制内容，看每一个draw call画了什么
然后，Nsight它是能看内存布局的，比如std140和std430的内存布局
更详细的我忘了，去年用的Nsight


