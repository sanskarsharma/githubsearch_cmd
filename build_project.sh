mvn clean package

echo "  ------------ Running TESTS --------------- "
source run_tests.sh

read -p "Do you want to run the program(y/n)  ?" input
case "$input" in
	y)
	java -jar target/githubsearch_cmd-0.0.1-SNAPSHOT-jar-with-dependencies.jar
	;;
	n)
	echo "ok bye"
esac