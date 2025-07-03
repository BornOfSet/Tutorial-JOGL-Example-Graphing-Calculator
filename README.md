Run Configurations:
添加VM arguments:
--add-exports java.base/java.lang=ALL-UNNAMED
--add-exports java.desktop/sun.awt=ALL-UNNAMED
--add-exports java.desktop/sun.java2d=ALL-UNNAMED
 -Dsun.awt.noerasebackground=true



 
右键你的项目-> Build Path ->Configure Build Path-> Add JARs，添加你的Jar包，Jar包包括Assimp for java,glue,jogl,glm等
assimp本程序没有用到，里面包括AiScene，AiMesh等用来导入模型的类
glue忘了是干啥的了
jogl包括GLCanvas，GLEventListener，GLAutoDrawable，FPSAnimator等核心类，这些就是Jogl的本体。程序是跑在GLEventListener里面的，每帧都会调用display方法，而init类似构造函数，reshape是当你重新设置视口宽高的时候调用的
glm包括在assimp里面。glm里面是各种向量类型，比如向量的normalize,向量之间的加减法，向量的哈希都包括在这里面
