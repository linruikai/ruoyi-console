d:
cd D:\�ļ�\ѧϰ����\git�ű�\test\src\test\java\com\ruoyi\console
set now=%date% %time%
echo %now%> ConsoleApplicationTests.java
cd D:\�ļ�\ѧϰ����\git�ű�\test
git add .
git commit -m "%now%"
git push origin master 
echo "Batch execution complete!"
pause