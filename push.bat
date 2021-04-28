d:
cd D:\文件\学习资料\git脚本\test\src\test\java\com\ruoyi\console
set now=%date% %time%
echo %now%> ConsoleApplicationTests.java
cd D:\文件\学习资料\git脚本\test
git add .
git commit -m "%now%"
git push origin master 
echo "Batch execution complete!"
pause