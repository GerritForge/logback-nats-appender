# Contributing
Everyone is welcome to contribute!

Patch submission can be done through [GerritHub](http://gerrithub.io) where patches are voted on by everyone in the community.
A patch usually requires a minimum of two +2 votes before it will be merged. +2 votes are reserved for
the core maintainers who can be contacted on the mailing list or in IRC.

If you are not familiar with Gerrit Code Review of GerritHub, you can watch the following videos on the
topics:
- [Gerrit Code Review presentation at EclipseCon](https://www.youtube.com/watch?v=Wxx8XndqZ7A)
- [GerritHub Introduciton](https://www.youtube.com/watch?v=jeWTvDad6VM)

If you are a casual contributor and are just using the GitHub on-line editing to fix typos in the
documentation, then you can issue a Pull-Request and we will import the code automatically into GerritHub
for review.

## Development Guidelines

These general guidelines help ensure that the development community remains fun, fair, and efficient.

Developers should strive to be active on [GerritHub](http://gerrithub.io) in order to stay in the loop on upcoming changes.
Coding and submission guidelines (i.e. being clear and concise in the commit message) should always be respected.
Developers do not need to add specific reviewers to patches. Instead, the maintainers and everyone else in the community
should always be on the lookout for incoming patches. If a developer would like to be added to a review, or would like
a specific person added to their patch, they should feel free to do so.
All comments on code reviews must be addressed prior to the patch being merged. Comments can be addressed by making code
changes or by replying to the comment.
There’s no minimum or maximum time for the life cycle of a patch. A patch may be accepted in hours or possibly weeks or
longer. How efficiently our community operates is a direct result of how well our community developers interact with each other.
Patch authors, including core maintainers, may not vote +1 or +2 on their own patches. They may vote -1 on their own patches
to signify that the patch should not be committed.

## GerritHub Configuration
You’ll log in to GerritHub using your GitHub account. Once logged in, in the top right corner click your user name and
select ‘Settings’. You should set up the following:

- Profile: Verify that the information is correct here.
- HTTP Password: Generate a password. You’ll use this password when prompted by git (not your GitHub password!).

Now that you’re configured, you can clone the GerritHub repository locally:

```bash
git clone https://review.gerrithub.io/gerritforge/logback-nats-appender
cd logback-nats-appender
```

Or if you already cloned directly from GitHub, you can change your repository to point at GerritHub by doing:

```bash
git remote set-url origin https://review.gerrithub.io/gerritforge/logback-nats-appender
```

When you later push a patch, you’ll be prompted for a password. That password is the one generated in the HTTP
Password section of the GerritHub settings, not your GitHub password.

Finally, you’ll need to install the Gerrit commit-msg hook. This inserts a unique change ID each time you
commit and is required for Gerrit to work.

```bash
curl -Lo .git/hooks/commit-msg https://review.gerrithub.io/tools/hooks/commit-msg
chmod +x .git/hooks/commit-msg
```

Now open .git/config in a text editor and add these lines: (this will make pushing reviews easier)

```ini
[remote "review"]
  url = https://review.gerrithub.io/gerritforge/logback-nats-appender
  push = HEAD:refs/for/master
```

Now you should be all set!

## Submitting a Patch

Submission Requirements:

- All commits must be signed off by the developer which indicates that you agree to the Developer Certificate of Origin.
  This is done using the -s or --signoff option when committing your changes.

- All commits must be formatted using [Google Java Format](https://github.com/google/google-java-format) v1.5.

- All new code must include accompanying unit test code.

- Commits should be rebased and all unit tests should be passing prior to submission.

- Squash commits prior to submitting a review such that each commit has a clear purpose that takes an incremental step toward
  the goal of the series of commits.

- Provide a clear commit message describing the purpose of the commit. Good commit messages provide a very brief summary
  of what the commit does in the title followed by a short paragraph providing context for the change. For example, what
  problem is being solved, how was it discovered and how this patch solves the problem.

Development is all done based on the master branch, so start by making sure you have the latest. The below assumes origin
is pointed at GerritHub.

```bash
git checkout master
git pull --rebase
```

Next, create a branch for your development work.

```bash
git checkout -b <my_branch>
```

Then, make your changes and commit as you go. You’ll build up a branch off of master with a series of commits.
Once you are done, pull the latest from master again and rebase your changes on top.

```bash
git checkout master
git pull
git checkout <my_branch>
git rebase -i master
```

Now your branch should be based on the tip of master and you should have the tip of checked out.
You can push your code to Gerrit for review by doing the following:

```bash
git push review
```

If prompted for a password, remember that it is the password from the HTTP Password section of the GerritHub settings.
If you enabled the git credential helper, you’ll only be prompted once.

*This contribution guidelines have been inspired by the [SPDK Project](https://github.com/spdk/spdk) which is based
on GerritHub*
