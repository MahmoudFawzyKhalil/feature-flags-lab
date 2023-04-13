# Problem statement
- We had a lot of stale branches that needed regular rebasing, with each rebase taking a long time and being error-prone
- How can we release these consistently to develop but without exposing the features unless they are ready for front end to consume them?

# What are feature flags?
Feature flags, in their simplest form, are **just if conditions in your code** that check if a certain feature is enabled or not. This allows us to deploy features even when they are not ready, meaning that our codebase is always deployable. This, in turn, enables continuous deployment even while the team is continuously pushing small commits to the main branch.

More **advanced feature flags allow us to target specific users**. Instead of enabling a feature for everyone, we only enable it for a cohort of our users. This allows us to release a feature progressively to more and more users. If something goes wrong, it only goes wrong for a handful of users.

# Advantages
- **Feature flags improve deployment frequency** because you can deploy any time. Even if there is unfinished code in the codebase, it will be hidden behind a feature flag. The main branch is always deployable.
- **Feature flags improve lead time** because a change can be deployed even if it’s not finished, yet, to gather feedback from key users.
- **Feature flags improve the mean time to restore** because you can revert a problematic change by just disabling the corresponding feature flag.
- **Feature flags improve change failure rate** because they decouple the risk of deployment with the risk of change. A deployment no longer fails and has to be rolled back because of bad features. The deployment is successful even if you have shipped a bad change because you can disable the bad change any time by flipping a feature flag.

## Potential problems
- I want to change the names of fields in an incoming DTO. Removing fields and adding new fields, is completely fine.
- I want to make a non-backwards compatible change to the database e.g. splitting a single column into two.
    - Copy the `entity Entity` x -> NewEntity x y (mapped to same table)
    - Copy the `repository Repository` NewRepository
    - Execute the migration script behind an endpoint (only when we are ready - when FE starts) col x -> col x1 col y1
    - Migrate data (e.g. split the column's data programmatically, and save it to the database) => this is always required even without feature flags
    - Toggle the flag
- Adding constraints e.g. check constraint, unique constraints to the database
- Adding a new relation FundingEntity => CollaborationProtocol - does not need any special treatment
- Renaming a field in a DTO is also the worst case

### Potential alleviations of the problems
- Only do the **Parallel Change Design Pattern (expand then contract)** step if the change is not backward compatible
- Only apply feature flags when necessary => frontend can't work on the task for extended periods

## Tradeoff
> Is the complexity of using feature flags worth the time saved on rebases and merge conflicts?

## Alternatives
> Stale branches are wasted effort. Do not work on anything that becomes **inventory** you have to manage. The problem is our work goes on a queue for the frontend to consume. -- Amin

# Resources
* [Feature Toggles (aka Feature Flags)](https://martinfowler.com/articles/feature-toggles.html)
* [Feature Flags with Spring Boot](https://reflectoring.io/spring-boot-feature-flags/)
* [Feature Flags: Make or Buy?](https://reflectoring.io/feature-flags-make-or-buy/)
* [Feature Flags in Java with Togglz and LaunchDarkly](https://reflectoring.io/java-feature-flags/)
* [Togglz - Features flag for Java](https://www.togglz.org/)