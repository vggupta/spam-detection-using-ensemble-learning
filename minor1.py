
    




import pandas as pd
import numpy as np
def warn(*args, **kwargs):
    pass
import warnings
warnings.warn = warn
from sklearn.cross_validation import train_test_split
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.naive_bayes import GaussianNB
from sklearn.decomposition import PCA
from sklearn.discriminant_analysis import LinearDiscriminantAnalysis as LDA
from sklearn.neighbors import KNeighborsClassifier
from sklearn import tree
from sklearn.naive_bayes import MultinomialNB
from sklearn import svm
import matplotlib.pyplot as plt
from sklearn.naive_bayes import BernoulliNB
from sklearn.ensemble import VotingClassifier

df=pd.read_csv('sms-spam',sep='\t',names=['status','message'])
#print(df.head())
#print("\n")
#print(len(df))
#print("\n")
#print(len(df[df.status=='spam']))
#print("\n")
df.loc[df["status"]=='ham',"status"]=1
df.loc[df["status"]=='spam',"status"]=0
#print(df.head())
#print("\n")
#a="Last weekends draw shows that you won a £1000 prize GUARANTEED. Call 09064012160. Claim Code K52. Valid 12hrs only. 150ppm"
df_x=df["message"]
#df_x.append(a);
#print(df_x)
#print(df_x)
df_y=df["status"]
x_train, x_test, y_train, y_test = train_test_split(df_x,df_y,test_size=0.2,random_state=0)

b=list(x_test)
#b.append(a)


print(b[len(b)-1])
tf1 = TfidfVectorizer(min_df=1,stop_words='english')
tf2 = tf1.fit_transform(x_train)
tf3 = tf1.transform(b)

c = tf3.toarray()
#d = []
"""print(tf3.shape)
for i in range(0,7531):
    d.append(c[len(c)-1][i])"""

#print(tf2.vocabulary)
#tf3 = tf1.transform(x_test)

y_train = y_train.astype('int')

"""clf3 = MultinomialNB()
clf3.fit(tf2.todense(), y_train)
pred = clf3.predict(d)
print(pred)"""


y_train = y_train.astype('int')
y_test = y_test.astype('int')
gnb_clf = GaussianNB()
mnb_clf = MultinomialNB()
#mnb_clf = KNeighborsClassifier(n_neighbors=1)
#bnb_clf = svm.SVC()
bnb_clf = BernoulliNB()
dtc_clf = tree.DecisionTreeClassifier()
"""
xyz=[]
p=[]
xyz.append(gnb_clf)
xyz.append(mnb_clf)
xyz.append(bnb_clf)
xyz.append(dtc_clf)
str=[]
str.append('gnb')
str.append('mnb')
str.append('bnb')
str.append('dtc')
"""
q=[]
q.append(('gnb',gnb_clf))
q.append(('mnb',mnb_clf))
q.append(('bnb',bnb_clf))
q.append(('dtc',dtc_clf))
d = tuple(q)

for i in range(1,16):
    estimator = []
    for j in range(0,4):
        if i & 1 << j:
            print(j+1)
            estimator.append(list(d[j]))
    if len(estimator) >0 :
        ensemble_clf = VotingClassifier(estimator , voting='hard')
        ens_clf = ensemble_clf.fit(tf2.toarray(), y_train)
        pred = ensemble_clf.predict(tf3.toarray())
        scr = ensemble_clf.score(tf3.toarray(),y_test)
        print(scr)

    del(estimator)


#pca = PCA()
"""pca1 = PCA(n_components=2)
trans = pd.DataFrame(pca1.fit_transform(tf2.toarray()))
print(trans)
x1 = list()
y1 = list()
x2 = list()
y2 = list()
dict = y_train.tolist()
for i in range (0,len(dict)):
    if(dict[i]==0):
        x1.append(trans[0][i])
        y1.append(trans[1][i])
    else:
        x2.append(trans[0][i])
        y2.append(trans[1][i])        
plt.scatter(x2, y2, label='ham', c='blue')
plt.scatter(x1, y1, label='spam', c='red')
plt.legend()
plt.show()"""
"""pca.fit(tf2.todense())
pca.fit(tf3.todense())"""
#tf4=tf3.todense()
#print("hello")
#print(tf3)
"""print(tf3)
print(tf3.todense().shape)"""
#print(tf2[0].todense())
#fp=open("stemp.txt","w")
#for i in range (0,4457):
# print(str(tf2[i].todense()))    
#fp.close()
#print(tf2[0][0].toarray())
#print(tf[1].todense())
#print("\n")
"""clf1 = GaussianNB()
y_train = y_train.astype('int')
y_test = y_test.astype('int')
clf1.fit(tf2.todense(), y_train)
pred = clf1.predict(tf3.todense())
sc = clf1.score(tf3.todense(),y_test)
print(sc)"""
"""clf3 = MultinomialNB()
pred = clf3.fit(tf2.todense(), y_train)
score = clf3.score(tf3.todense(),y_test)
print(score)"""
"""neigh = KNeighborsClassifier(n_neighbors=1)
neigh.fit(tf2.todense(), y_train)
scr = neigh.score(tf3.todense(),y_test)
print(scr)
clf2 = tree.DecisionTreeClassifier()
pred = clf2.fit(tf2.todense(), y_train)
scrr = clf2.score(tf3.todense(),y_test)
print(scrr)"""
"""arr = np.array(y_test)
count=0
for i in range (len(pred)):
    if pred[i]==arr[i]:
        count=count+1
acc = (count)/(len(pred))
print(acc)"""
