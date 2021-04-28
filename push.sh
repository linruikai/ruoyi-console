echo "begin push"

git add .

set now=%date% %time%
git commit -m "%now%"

git push

echo "Batch execution complete!"