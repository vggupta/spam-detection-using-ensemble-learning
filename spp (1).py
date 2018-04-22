import socket                                         
import time
import csv
def warn(*args, **kwargs):
    pass
import warnings
warnings.warn = warn
from sklearn.cross_validation import train_test_split
import numpy as np
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.decomposition import PCA
from sklearn.naive_bayes import GaussianNB
from sklearn.naive_bayes import MultinomialNB
from sklearn import tree
from sklearn.naive_bayes import BernoulliNB
from sklearn.ensemble import VotingClassifier


with open("sms-spam","r") as smsSpam :
    smsSpamReader = csv.reader(smsSpam, delimiter='\t')
    X = []
    Y = []
    for row in smsSpamReader:
        if len(row) != 0:
            if row[0:1]==['spam']:
                Y.append(0)
            else :
                Y.append(1)
            X.append(row[1:len(row)][0])
smsSpam.close()

x_train, x_test, y_train, y_test = train_test_split(X,Y,test_size=0.2,random_state=0)


serversocket = socket.socket(
	        socket.AF_INET, socket.SOCK_STREAM) 

host = "192.168.43.101"                           

port = 8000                                          

serversocket.bind(("192.168.43.101",8000))                                  

serversocket.listen(5)                                           

while True:
    print('Listening')
    clientsocket,addr = serversocket.accept()
    z = clientsocket.recv(20240)
    x = z.decode('utf8')
    print(x)
    b =list(x_test)
    b.append(x)
    tf = TfidfVectorizer(min_df=1,stop_words='english')
    tf_train = tf.fit_transform(np.array(x_train))
    tf_test = tf.transform(b)

    c = tf_test.toarray()
    d = []
    for i in range(0,tf_test.shape[1]):
        d.append(c[len(c)-1][i])

    gnb_clf = GaussianNB()
    #mnb_clf = MultinomialNB()
    bnb_clf = BernoulliNB()
    dtc_clf = tree.DecisionTreeClassifier()

    ensemble_clf = VotingClassifier(estimators=[('gnb',gnb_clf),('bnb',bnb_clf),('dt',dtc_clf)] , voting='hard')
    ens_clf = ensemble_clf.fit(tf_train.toarray(), y_train)
    pred = ensemble_clf.predict(d)

    var = pred[0] 

    if pred[0]==0:
        y="SPAM"
    else:
        y="HAM"
    clientsocket.send(y.encode('ascii'))
    clientsocket.close()
###########################################################################################################################
                    #################Machine learning end################################
