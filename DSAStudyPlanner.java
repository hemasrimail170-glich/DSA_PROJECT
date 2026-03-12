import java.util.*;

/*
ABSTRACT

Students often struggle to manage study schedules when many topics must be
completed within limited time. This program converts syllabus topics into
structured daily study tasks. Topics are distributed across study days and
short explanations are provided.
*/

public class DSAStudyPlanner {

    /* CO4: HashMap for fast topic explanation lookup */
    static HashMap<String,String[]> explanations = new HashMap<>();

    /* CO2: LinkedList ADT to store topics */
    static LinkedList<String> topicList = new LinkedList<>();

    /* CO3: Stack to track completed topics */
    static Stack<String> completedTopics = new Stack<>();


    /* Load explanations */
    static void loadExplanations(){

        explanations.put("Internet Fundamentals", new String[]{
                "Internet connects computers worldwide.",
                "It allows communication between devices.",
                "It uses protocols like TCP/IP."
        });

        explanations.put("CSS Syntax", new String[]{
                "CSS controls style of web pages.",
                "It uses selector, property and value.",
                "Example: color:red;"
        });

        explanations.put("Flexbox", new String[]{
                "Flexbox is a CSS layout model.",
                "It arranges elements in rows or columns.",
                "It helps build responsive layouts."
        });

        explanations.put("HTTP/HTTPS Protocols", new String[]{
                "HTTP transfers web data.",
                "HTTPS adds encryption security.",
                "It protects user information."
        });

    }


    /* CO1: Bubble Sort */
    static void bubbleSort(){

        for(int i=0;i<topicList.size()-1;i++){

            for(int j=0;j<topicList.size()-i-1;j++){

                if(topicList.get(j).compareToIgnoreCase(topicList.get(j+1))>0){

                    String temp = topicList.get(j);
                    topicList.set(j,topicList.get(j+1));
                    topicList.set(j+1,temp);

                }

            }

        }

    }


    /* CO1: Linear Search */
    static int linearSearch(String key){

        for(int i=0;i<topicList.size();i++){

            if(topicList.get(i).equalsIgnoreCase(key))
                return i;

        }

        return -1;
    }


    /* CO2: LinkedList ADT operations */
    static void linkedListOperations(){

        System.out.println("\n--- CO2 LinkedList ADT Operations ---");

        System.out.println("Traverse topics:");
        for(String t: topicList)
            System.out.println(t);

    }


    /* CO3: Queue scheduling */
    static void generatePlan(int days,int perDay){

        Queue<String> queue = new LinkedList<>(topicList);

        System.out.println("\n===== STUDY PLAN =====");

        for(int d=1; d<=days; d++){

            System.out.println("\nDay "+d);

            for(int i=0;i<perDay;i++){

                if(queue.isEmpty()){
                    System.out.println("No topics left for today.");
                    break;
                }

                String topic = queue.poll();

                System.out.println("\nTopic: "+topic);

                boolean found=false;

                for(String key: explanations.keySet()){

                    if(key.equalsIgnoreCase(topic)){

                        for(String line: explanations.get(key))
                            System.out.println(line);

                        found=true;
                        break;

                    }

                }

                if(!found){

                    System.out.println("Study theoretical concept.");
                    System.out.println("Understand applications.");
                    System.out.println("Practice examples.");

                }

                /* CO3: Stack push (completed topic) */
                completedTopics.push(topic);

            }

        }

    }


    /* CO5: Practical application of Linear Data Structures */
    static void showCompletedTopics(){

        System.out.println("\n--- Completed Topics (Stack) ---");

        while(!completedTopics.isEmpty()){

            System.out.println(completedTopics.pop());

        }

    }



    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        loadExplanations();

        System.out.println("===== STUDY PLANNER =====");

        System.out.print("Enter number of topics: ");
        int n = sc.nextInt();
        sc.nextLine();

        /* CO2: Insert topics into LinkedList */
        for(int i=0;i<n;i++){

            System.out.print("Enter topic "+(i+1)+": ");
            topicList.add(sc.nextLine());

        }

        bubbleSort(); // CO1 sorting

        linkedListOperations(); // CO2 operations

        System.out.print("\nEnter number of study days: ");
        int days = sc.nextInt();

        System.out.print("Enter topics per day: ");
        int perDay = sc.nextInt();

        generatePlan(days,perDay); // CO3 queue

        showCompletedTopics(); // CO5 application

        sc.nextLine();

        System.out.print("\nSearch topic: ");
        String search = sc.nextLine();

        int index = linearSearch(search); // CO1 search

        if(index!=-1)
            System.out.println("Topic found at position "+index);
        else
            System.out.println("Topic not found");

    }

}