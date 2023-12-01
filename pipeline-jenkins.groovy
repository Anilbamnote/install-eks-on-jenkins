pipeline{
    agent any 
    parameters{
        string(name: "Name-Cluster",defaultValue: "cluster-project")
        choice(name: "Select-region",choices:["us-east-1","us-east-2","ap-south-1"])
    }
    stages{
        stage('Install AWS CLI and eksctl') {                //(**AWS configure manually in jenkins user with secret access key before doing below steps**)
            steps {
                script {
                    sh 'sudo apt-get update -y'
                    sh 'sudo apt-get install -y awscli'
                    sh 'sudo curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp'
                    sh 'sudo mv /tmp/eksctl /usr/local/bin'
                }
            }
        }

        stage('stage1'){
            steps{
                echo "name of cluster $params.Name-Cluster"
                sh 'eksctl create cluster --name my-clus-0421 --region us-east-1'
            }
        }
        stage('test'){
            steps{
                echo "name of region $params.Select-region"
            }
        }
    }
}





**configure aws on jenkins user**
  
Last login: Thu Nov 30 05:43:12 2023 from 18.206.107.29
ubuntu@ip-172-31-16-58:~$ sudo su jenkins
jenkins@ip-172-31-16-58:/home/ubuntu$ cd 
jenkins@ip-172-31-16-58:~$ ls
jenkins@ip-172-31-16-58:~$ aws configure
AWS Access Key ID [None]: 
AWS Secret Access Key [None]: 
Default region name [None]: 
Default output format [None]: 
jenkins@ip-172-31-16-58:~$ 
exit
ubuntu@ip-172-31-16-58:~$ sudo -i
root@ip-172-31-16-58:~# ls
 snap  '{'


**change user permision**
  
root@ip-172-31-16-58:~# cd /etc
vim sudoers file
****and add new jenkins user with all permision****
# User privilege specification
root    ALL=(ALL:ALL) ALL
jenkins ALL=(ALL) NOPASSWD:ALL
# Members of the admin group may gain root privileges
%admin ALL=(ALL) ALL

goto jenkins build job wit pwrameter



  


